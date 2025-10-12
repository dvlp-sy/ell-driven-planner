package ell.app.group.application;

import ell.app.group.domain.Group;
import ell.app.group.domain.GroupGenerator;
import ell.app.group.domain.GroupUpdater;
import ell.app.group.infrastructure.GroupRepository;
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
