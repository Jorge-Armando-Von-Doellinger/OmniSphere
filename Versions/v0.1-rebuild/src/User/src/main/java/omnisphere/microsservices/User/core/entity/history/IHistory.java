package omnisphere.microsservices.User.core.entity.history;

import lombok.Data;

import java.util.List;

public interface IHistory<TUser, TUserUpdate, TUserBlock> {
    TUser user();
    List<TUserUpdate> updates();
    List<TUserBlock> blocks();
}
