package com.devon.building.repository.Impl;

import com.devon.building.entity.BuildingEntity;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.repository.BuildingRepositoryCustom;
import com.devon.building.utils.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private void appendJoin(StringBuilder join, BuildingSearchRequest buildingSearchRequest) {

        Long staffid = buildingSearchRequest.getStaffId();

        if (StringUtil.CheckNull(staffid)) {
            join.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
        }


        Long rentAreaFrom = buildingSearchRequest.getRentAreaFrom();

        Long rentAreaTo = buildingSearchRequest.getRentAreaTo();

        if (StringUtil.CheckNull(rentAreaFrom) || StringUtil.CheckNull(rentAreaTo)) {
            join.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid ");
        }

    }

    private void appendWhere(StringBuilder where, BuildingSearchRequest buildingSearchRequest) {

        // normal

        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                Object value = field.get(buildingSearchRequest);

                if (value == null
                        || fieldName.equals("staffId")
                        || fieldName.equals("typeCode")
                        || fieldName.startsWith("rentArea")
                        || fieldName.startsWith("rentPrice")) {
                    continue;
                }

                if (value instanceof String str) {
                    if (StringUtil.hasText(str)) {
                        where.append(" AND b.").append(fieldName.toLowerCase()).append(" LIKE '%").append(str.trim()).append("%'");
                    }
                }

                if (value instanceof Long || value instanceof Integer) {
                    where.append(" AND b.").append(fieldName.toString()).append(" = ").append(value);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // special
        Long staffId = buildingSearchRequest.getStaffId();
        if (StringUtil.CheckNull(staffId)) {
            where.append(" AND ab.staffid =  " + staffId);
        }

        Long rentAreaFrom = buildingSearchRequest.getRentAreaFrom();
        Long rentAreaTo = buildingSearchRequest.getRentAreaTo();

        if (StringUtil.CheckNull(rentAreaFrom) || StringUtil.CheckNull(rentAreaTo)) {

            if (StringUtil.CheckNull(rentAreaFrom)) {
                where.append(" AND ra.value >= " + rentAreaFrom);
            }

            if (StringUtil.CheckNull(rentAreaTo)) {
                where.append(" AND ra.value <= " + rentAreaTo);
            }
        }
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();

        if (rentPriceFrom != null) {
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }

        if (rentPriceTo != null) {
            where.append(" AND b.rentprice <= " + rentPriceTo);
        }

        List<String> typeCode = buildingSearchRequest.getTypeCode();

        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" AND (");

            String condition = typeCode.stream()
                    .map(it -> " b.`type` LIKE '%" + it + "%' ")
                    .collect(Collectors.joining(" OR "));

            where.append(condition);
            where.append(")");
        }

    }

    @Override
    public List<BuildingEntity> searchBuildings(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder(

                "SELECT DISTINCT b.* FROM building b ");
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");

        appendJoin(sql, buildingSearchRequest);

        appendWhere(where, buildingSearchRequest);

        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

        return query.getResultList();

    }

}