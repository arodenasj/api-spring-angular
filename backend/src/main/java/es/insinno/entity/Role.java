package es.insinno.entity;

    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "roles")
    @Data
    @NoArgsConstructor
    public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, unique = true)
        private RoleEnum name;

        public Role(RoleEnum name) {
            this.name = name;
        }
    }