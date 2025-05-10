package omnisphere.microsservices.User.core.entity.history;

import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.UserUpdate;

import java.util.List;


public record UserHistory (User user, List<UserUpdate> updates, List<UserBlock> blocks)
        implements IHistory<User, UserUpdate, UserBlock> {

}
