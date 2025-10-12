package app.group.application;

import app.group.domain.Group;
import app.group.domain.GroupGenerator;
import app.group.domain.GroupUpdater;
import app.group.infrastructure.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public void createGroup(GroupGenerator groupGenerator) {
        groupRepository.save(groupGenerator.toDocument());
    }

    public Group getGroup(String id) {
        return Group.from(groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with id: " + id)));
    }

    public void updateGroup(GroupUpdater groupUpdater) {
        groupRepository.save(groupUpdater.toDocument());
    }

    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }
}
