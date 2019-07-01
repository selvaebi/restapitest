package uk.ac.ebi.test.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"})})
public class Person {

    @Id
    @GeneratedValue
    @ApiModelProperty(position = 1, value = "Auto generated id", readOnly = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @ApiModelProperty(position = 2, required = true)
    @NotNull
    @Column(nullable = false)
    private String first_name;

    @ApiModelProperty(position = 3, required = true)
    @NotNull
    @Column(nullable = false)
    private String last_name;

    @ApiModelProperty(position = 4, required = true, example = "10")
    @NotNull
    @Min(1)
    @Column(nullable = false)
    private int age;

    @ApiModelProperty(position = 5, required = true)
    @NotNull
    @Column(nullable = false)
    private String favourite_color;

    @ApiModelProperty(position = 6)
    @ElementCollection
    private List<String> hobby;

    public long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavourite_color() {
        return favourite_color;
    }

    public void setFavourite_color(String favourite_color) {
        this.favourite_color = favourite_color;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }
}
