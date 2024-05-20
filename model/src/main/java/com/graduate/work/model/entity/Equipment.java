package com.graduate.work.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@ToString
public class Equipment {

    @Id
    @Getter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Getter
    @Setter
    String hostname;

    @Getter
    @Setter
    String model;

    @Getter
    @Setter
    String serialNumber;

    @OneToOne
    @ToString.Exclude
    private Object object;

    public void setObject(Object object) {
        this.object = object;
        if (object != null)
            object.setEquipment(this);
    }

    @OneToMany(mappedBy = "equipment")
    @ToString.Exclude
    @Builder.Default
    private List<Modem> modems = new ArrayList<>();

    public int sizeModems() {
        return modems.size();
    }

    public void addModem(Modem modem)
    {
        modems.add(modem);
        if (modem.getEquipment() != this) {
            modem.setEquipment(this);
        }
    }

    public Modem getModem(int id)
    {
        return modems.get(id);
    }

    public boolean containsModem(Modem modem)
    {
        return modems.contains(modem);
    }

    public void removeModem(Modem modem)
    {
        modems.remove(modem);
        modem.setEquipment(null);
    }


}