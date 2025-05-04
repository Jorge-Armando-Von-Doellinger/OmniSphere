package omnisphere.microsservices.User.core.entity.history;

import lombok.Data;
import omnisphere.microsservices.User.core.entity.User;
import omnisphere.microsservices.User.core.entity.UserBlock;
import omnisphere.microsservices.User.core.entity.UserUpdate;

import java.util.List;

@Data
public class UserHistory {
    private User user;
    private List<UserUpdate> updates;
    private List<UserBlock> blocks;
}
