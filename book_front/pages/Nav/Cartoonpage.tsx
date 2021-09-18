import React from 'react';
import Catalog from '../../components/Catalog/Catalog';
import styled from 'styled-components';

const CartoonPage = (): JSX.Element => {
	return (
		<CatalogBg>
			<Catalog catal="cartoon" header="만화" />
		</CatalogBg>
	);
};

const CatalogBg = styled.div``;
export default CartoonPage;
