public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
  Optional<UserAccount> findByUsername(String u);
}
