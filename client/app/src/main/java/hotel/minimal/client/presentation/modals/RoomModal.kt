package hotel.minimal.client.presentation.modals

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import hotel.minimal.client.App
import hotel.minimal.client.R
import hotel.minimal.client.databinding.RoomModalBinding
import hotel.minimal.client.domain.models.RoomPopulated
import hotel.minimal.client.domain.models.Type
import hotel.minimal.client.domain.DefaultValue
import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.presentation.viewModels.RoomViewModel
import hotel.minimal.client.presentation.viewModels.TypeViewModel
import loshica.vendor.view.LOSDialogBuilder
import javax.inject.Inject

class RoomModal : DialogFragment(), View.OnClickListener {

    private var layout: RoomModalBinding? = null

    @Inject
    lateinit var roomViewModel: RoomViewModel

    @Inject
    lateinit var typeViewModel: TypeViewModel

    private var typesListObserver: Observer<List<Type>>? = null

    private var typeId: Int = DefaultValue.ROOM.type.id
    private var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as App).rootInjector.inject(this)
        super.onCreate(savedInstanceState)

        arguments?.let {
            isEdit = it.getBoolean(IS_EDIT)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = LOSDialogBuilder(requireActivity())

        layout = RoomModalBinding.inflate(requireActivity().layoutInflater)

        if (isEdit) {
            typeId = roomViewModel.getCurrentRoom().type.id
        }

        with(layout!!) {
            if (isEdit) {
                val room: RoomPopulated = roomViewModel.getCurrentRoom()

                roomAddressInput.setText(room.address)
                roomDescriptionInput.setText(room.description)
                roomFloorInput.setText(room.floor.toString())
                roomPlacesInput.setText(room.places.toString())
            }
        }

        typesListObserver = Observer { onChangeTypes(it) }

        val titleRef: Int = if (isEdit) {
            R.string.change_room_modal_header
        } else {
            R.string.create_room_modal_header
        }

        val positiveButtonRef: Int = if (isEdit) R.string.change else R.string.create

        return builder
            .setView(layout!!.root)
            .setTitle(requireActivity().resources.getText(titleRef))
            .setPositiveButton(positiveButtonRef) { dialog, _ -> onSubmit(dialog) }
            .create()
    }

    private fun onSubmit(dialog: DialogInterface) {
        layout?.let {
            try {
                val address: String? = it.roomAddressInput.text?.toString()
                val description: String? = it.roomDescriptionInput.text?.toString()
                val floor: Int? = it.roomFloorInput.text?.toString()?.toInt()
                val places: Int? = it.roomPlacesInput.text?.toString()?.toInt()

                val isAddressValid: Boolean = !address.isNullOrBlank()
                val isDescriptionValid: Boolean = !description.isNullOrBlank()

                if (!(isAddressValid && isDescriptionValid && floor != null && places != null)) {
                    throw Exception()
                }

                val dto = Room(
                    type = typeId,
                    address = address!!,
                    description = description!!,
                    floor = floor,
                    places = places
                )

                if (isEdit) {
                    roomViewModel.updateRoom(dto)
                } else {
                    roomViewModel.createRoom(dto)
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Invalid data", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        typesListObserver?.let { typeViewModel.typesList.observe(this, it) }
    }

    override fun onStop() {
        super.onStop()
        typesListObserver?.let { typeViewModel.typesList.removeObserver(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout = null
    }

    override fun onClick(v: View?) {
        layout?.let {
            if (v is RadioButton) {
                typeId = v.id
                println(typeId)
            }
        }
    }

    private fun onChangeTypes(types: List<Type>) {
        layout?.let { layout ->
            typeId = try {
                if (isEdit) roomViewModel.getCurrentRoom().type.id else types[0].id
            } catch (e: Exception) {
                -1
            }

            types.forEach { type ->
                val radioButton = RadioButton(context)

                radioButton.id = type.id
                radioButton.text = type.name
                radioButton.isChecked = radioButton.id == typeId

                radioButton.setOnClickListener(this)
                layout.typeRadioGroup.addView(radioButton)
            }
        }
    }

    companion object {
        private const val IS_EDIT = "isEdit"

        @JvmStatic
        fun newInstance(isEdit: Boolean) =
            RoomModal().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_EDIT, isEdit)
                }
            }
    }
}