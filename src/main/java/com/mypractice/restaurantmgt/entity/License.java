package com.mypractice.restaurantmgt.entity;

import com.mypractice.restaurantmgt.enums.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "licenses")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_id")
    private Long licenseNo;

    @Column(name = "license_no", length = 15, unique = true)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "doc_type", length = 100)
    private DocumentType type;

    @Column(name = "document_name", length = 150)
    private String documentName;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive;
}
