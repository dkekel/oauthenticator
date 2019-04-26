package ch.meyrin.lakeflower.oauthenticator.service;

import ch.meyrin.lakeflower.oauthenticator.entity.user.Account;
import ch.meyrin.lakeflower.oauthenticator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class OAuthUserDetailsService implements UserDetailsManager {

    private final transient UserRepository userRepository;

    @Autowired
    public OAuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findAccountByUsername(username);
    }

    @Override
    public void createUser(UserDetails user) {
        userRepository.save((Account) user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findAccountByUsername(username) != null;
    }
}
