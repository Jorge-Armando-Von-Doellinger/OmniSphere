package omnisphere.microsservices.User.core.services.interfaces.admin;

import omnisphere.microsservices.User.core.entity.remove_representation.UserRemoved;
import omnisphere.microsservices.User.core.entity.remove_representation.UserUpdateRemoved;
import omnisphere.microsservices.User.core.services.interfaces.admin.base.IBasicUserManagement;
import reactor.core.publisher.Flux;

/// Do a management of the users deleted!
/// OBS: CANNOT DELETE OR UPDATE THIS DATA, BECAUSE ALREADY BE DELETED!
public interface IRemovedUserManagementService extends IBasicUserManagement<UserRemoved, UserUpdateRemoved> {
    Flux<UserRemoved> findByEmail(String email);
}
