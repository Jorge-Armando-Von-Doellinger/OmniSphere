package omnisphere.microsservices.User.core.entity.history;

import lombok.Data;
import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.remove_representation.UserUpdateRemoved;

import java.util.List;

@Data
public class UserRemovedHistory {
    private UserRemoved user;
    private List<UserUpdateRemoved> updates;
    private List<UserBlockRemoved> blocks;
}
