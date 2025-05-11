package omnisphere.microsservices.User.core.entity.history;

import java.util.List;

public interface IHistory<TUser, TUserUpdate, TUserBlock> {
    TUser current();
    List<TUserUpdate> updates();
    List<TUserBlock> blocks();
}
