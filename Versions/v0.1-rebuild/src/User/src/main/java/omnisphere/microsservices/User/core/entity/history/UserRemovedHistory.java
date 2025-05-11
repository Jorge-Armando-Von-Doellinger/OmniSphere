package omnisphere.microsservices.User.core.entity.history;

import omnisphere.microsservices.User.core.entity.remove_representation.UserBlockRemoved;
import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.remove_representation.OldUserRemoved;

import java.util.List;

public record UserRemovedHistory (UserRemoved current, List<OldUserRemoved> updates, List<UserBlockRemoved> blocks)
        implements IHistory<UserRemoved, OldUserRemoved, UserBlockRemoved> { }
