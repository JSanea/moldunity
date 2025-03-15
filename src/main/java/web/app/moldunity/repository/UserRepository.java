package web.app.moldunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.app.moldunity.entity.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.username = ?1")
    Optional<User> findByName(String name);

    @Query(value = "select username from users where username = :username", nativeQuery = true)
    String existUsername(@Param("username") String username);

    @Query(value = "select email from users where email = :email", nativeQuery = true)
    String existEmail(@Param("email") String email);

    @Query(value = "select password from users where username = :username", nativeQuery = true)
    String getPasswordByUsername(@Param("username") String username);

    @Query(value = "select role from users where username = :username", nativeQuery = true)
    String getRoleByUsername(@Param("username") String username);

    @Query(value = "select password, role from users where username = :username", nativeQuery = true)
    List<String> getPasswordAndRoleByUsername(@Param("username") String username);

    @Query(value = "select u from User u " +
            "left join fetch u.kitchenFurnitures " +
            "left join fetch u.bathroomFurnitures " +
            "left join fetch u.bedroomFurnitures " +
            "left join fetch u.diningFurnitures " +
            "left join fetch u.sofaArmchairs " +
            "left join fetch u.tableChairs " +
            "left join fetch u.wardrobes " +
            "left join fetch u.officeFurnitures " +
            "left join fetch u.otherFurnitures " +
            "left join fetch u.freezers " +
            "left join fetch u.washingMachines " +
            "left join fetch u.dryers " +
            "left join fetch u.dishWashers " +
            "left join fetch u.cookers " +
            "left join fetch u.hobs " +
            "left join fetch u.ovens " +
            "left join fetch u.microwaveOvens " +
            "left join fetch u.vacuumCleaners " +
            "left join fetch u.kitchenAppliances " +
            "left join fetch u.irons " +
            "left join fetch u.climates " +
            "left join fetch u.otherAppliances " +
            "left join fetch u.televisors " +
            "left join fetch u.monitors " +
            "left join fetch u.laptopTablets " +
            "left join fetch u.smartphones " +
            "left join fetch u.computers " +
            "where u.username = ?1")
    Optional<User> getByName(String username);
}
