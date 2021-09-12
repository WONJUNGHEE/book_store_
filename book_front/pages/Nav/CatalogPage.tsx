import React from 'react';
import Catalog from '../../components/Catalog/Catalog';
import styled from 'styled-components';

const CatalogPage = (): JSX.Element => {
	return (
		<CatalogBg>
			<Catalog catal="All" />
		</CatalogBg>
	);
};

const CatalogBg = styled.div``;
export default CatalogPage;
