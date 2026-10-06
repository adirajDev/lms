package dev.adi.lms.features.author;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(name = "birth_year")
    private Short birthYear;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 2)
    private String country;

    protected Author() {}

    public Author(String fullName, Short birthYear, String country) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Short getBirthYear() { return birthYear; }

    public void setBirthYear(short birthYear) {
        this.birthYear = birthYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthYear=" + birthYear +
                ", country='" + country + '\'' +
                '}';
    }
}
