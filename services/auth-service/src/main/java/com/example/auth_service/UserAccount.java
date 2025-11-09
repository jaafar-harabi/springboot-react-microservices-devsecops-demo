@Entity @Table(name="users")
public class UserAccount {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
  @Column(unique=true,nullable=false) String username;
  @Column(nullable=false) String passwordHash;
  @Column(nullable=false) String role="USER";
  Instant createdAt=Instant.now();
  
}
