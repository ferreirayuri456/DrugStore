package br.com.example.farmacia.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.model.dto.ProductDTO;

public class SpecificationProduct {

	public static final String NAME_PRODUCT = "nameProduct";
	public static final String FANTASY_NAME = "fantasyName";

	public static Specification<Product> findBySpecification(ProductDTO filtro) {
		return new Specification<Product>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicados = new ArrayList<>();
				
				if (StringUtils.isNotEmpty(filtro.getFantasyName())) {
					predicados.add(criteriaBuilder.equal(root.get(FANTASY_NAME), filtro.getFantasyName()));
				}
				
				if (StringUtils.isNotEmpty(filtro.getNameProduct())) {
					predicados.add(criteriaBuilder.equal(root.get(NAME_PRODUCT), filtro.getNameProduct()));
					
				}
				query.distinct(true);
				return criteriaBuilder.and(predicados.toArray(new Predicate[] {}));
			}

		};
	}

}
