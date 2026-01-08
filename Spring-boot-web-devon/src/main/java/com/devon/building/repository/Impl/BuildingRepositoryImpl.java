package com.devon.building.repository.Impl;

import com.devon.building.builder.BuildingSearchBuilder;
import com.devon.building.entity.Building;
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

    private void appendJoin(StringBuilder join, BuildingSearchBuilder buildingSearchBuilder) {
        if (StringUtil.CheckList(buildingSearchBuilder.getTypeCode())) {
            join.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
            join.append(" INNER JOIN renttype rt ON buildingrenttype.renttypeid = rt.id ");
        }
        Long staffid = buildingSearchBuilder.getStaffId();

        if (StringUtil.CheckNull(staffid)) {
            join.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
        }

        Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();

        Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();

        if (StringUtil.CheckNull(rentAreaFrom) || StringUtil.CheckNull(rentAreaTo)) {
            join.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid ");
        }

    }

    private void appendWhere(StringBuilder where, BuildingSearchBuilder buildingSearchBuilder) {

        // normal

        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);

                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentArea")
                        && !fieldName.startsWith("rentPrice")) {

                    Object value = field.get(buildingSearchBuilder);

                    if (value != null) {
                        if (field.getType().getName().equals("java.lang.Long")
                                || field.getType().getName().equals("java.lang.Integer")) {
                            if (fieldName.equals("name")) {
                                where.append(" AND b." + fieldName.toLowerCase() + " LIKE '%" + value + "%'");
                                continue;
                            }
                            if (fieldName.equals("street")) {
                                where.append(" AND b." + fieldName.toLowerCase() + " LIKE '%" + value + "%'");
                                continue;
                            }
                            if (fieldName.equals("ward")) {
                                where.append(" AND b." + fieldName.toLowerCase() + " LIKE '%" + value + "%'");
                                continue;
                            }

                            where.append(" AND b." + fieldName.toLowerCase() + " = " + value);

                        } else if (field.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b." + fieldName.toLowerCase() + " LIKE '%" + value + "%'");

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // special
        Long staffId = buildingSearchBuilder.getStaffId();
        if (StringUtil.CheckNull(staffId)) {
            where.append(" AND ab.staffid =  " + staffId);
        }

        Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();

        if (StringUtil.CheckNull(rentAreaFrom) || StringUtil.CheckNull(rentAreaTo)) {

            if (StringUtil.CheckNull(rentAreaFrom)) {
                where.append(" AND ra.value >= " + rentAreaFrom);
            }

            if (StringUtil.CheckNull(rentAreaTo)) {
                where.append(" AND ra.value <= " + rentAreaTo);
            }
        }
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();

        if (rentPriceFrom!= null) {
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }

        if (rentPriceTo!= null) {
            where.append(" AND b.rentprice <= " +rentPriceTo);
        }

        List<String> typeCode = buildingSearchBuilder.getTypeCode();

        if (typeCode != null) {
            where.append(
                            " AND rt.code IN(" + typeCode.stream().map(it -> "'" + it + "'").collect(Collectors.joining(",")))
                    .append(")");
        }

    }

    @Override
    public List<Building> searchBuildings(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder(
                "SELECT b.* FROM building b ");
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");

        appendJoin(sql, buildingSearchBuilder);

        appendWhere(where, buildingSearchBuilder);

        sql.append(where);
        sql.append(" GROUP BY b.id");

        Query query = entityManager.createNativeQuery(sql.toString(), Building.class);

        return query.getResultList();

    }

}