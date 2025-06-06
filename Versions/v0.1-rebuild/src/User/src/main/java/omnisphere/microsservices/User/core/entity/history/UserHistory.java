package omnisphere.microsservices.User.core.entity.history;

import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.OldUser;

import java.util.List;


public record UserHistory (User current, List<OldUser> updates, List<UserBlock> blocks)
        implements IHistory<User, OldUser, UserBlock> {

}
