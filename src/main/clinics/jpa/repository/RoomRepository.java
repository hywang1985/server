package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Room;

@Repository
public interface RoomRepository extends BaseRepository<Room, Integer> {

	public List<Room> findAllByNameLike(String name);

	public List<Room> findByAllotable(Boolean allotable);

}
