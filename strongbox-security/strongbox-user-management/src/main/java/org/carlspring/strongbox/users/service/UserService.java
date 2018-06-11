package org.carlspring.strongbox.users.service;

import org.carlspring.strongbox.users.domain.MutableAccessModel;
import org.carlspring.strongbox.users.domain.User;
import org.carlspring.strongbox.users.domain.Users;
import org.carlspring.strongbox.users.domain.MutableUser;
import org.carlspring.strongbox.users.domain.MutableUsers;

import org.jose4j.lang.JoseException;

/**
 * @author Alex Oreshkevich
 * @author Przemyslaw Fusik
 */
public interface UserService
{

    User findByUserName(String username);

    /**
     * Generates another one 'Security Token' for specific user.<br>
     * Token will be based on 'username' with 'securityTokenKey' used as clam.
     *
     * @param username
     *            user ID
     * @return encrypted token
     * @throws JoseException
     */
    String generateSecurityToken(String username)
            throws JoseException;

    /**
     * Generates 'Authentication Token' for specific user.<br>
     * This token can be used for JWT Authentication.
     *
     * @param username     user ID
     * @param expireSeconds token expiration in seconds (endless if empty)
     * @return encrypted token
     * @throws JoseException
     */
    String generateAuthenticationToken(String username,
                                       Integer expireSeconds)
            throws JoseException;

    /**
     * @param username
     * @param token
     */
    void verifySecurityToken(String username,
                             String token);

    void updatePassword(MutableUser userToUpdate);

    void updateByUsername(MutableUser userToUpdate);

    void setUsers(MutableUsers users);

    Users findAll();

    void revokeEveryone(String roleToRevoke);

    void add(MutableUser user);

    void delete(String username);

    void updateAccessModel(String username,
                           MutableAccessModel accessModel);
}
