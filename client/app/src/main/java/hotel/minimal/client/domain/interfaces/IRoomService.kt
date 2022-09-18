package hotel.minimal.client.domain.interfaces

import hotel.minimal.client.domain.models.Room
import hotel.minimal.client.domain.models.RoomPopulated

interface IRoomService : ICrudService<RoomPopulated, Room>