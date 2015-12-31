package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Room;
import clinics.jpa.repository.RoomRepository;
import clinics.utils.Constants;

@Service
public class RoomRepositoryService extends AbstractRepositoryService<RoomRepository, Room, Integer> {

	@Autowired
	private RoomRepository repository;

	@Override
	public RoomRepository repository() {
		return repository;
	}

	public List<Room> findByName(String name) {
		return repository().findAllByNameLike(Constants.PERCENT + name.toUpperCase() + Constants.PERCENT);
	}

	public List<Room> findByAllotable(Boolean allotable) {
		return repository().findByAllotable(allotable);
	}
}
