import React from 'react';
import Catalog from '../../components/Catalog/Catalog';
import styled from 'styled-components';

const EduPage = (): JSX.Element => {
	return (
		<CatalogBg>
			<Catalog catal="edu" />
		</CatalogBg>
	);
};

const CatalogBg = styled.div``;
export default EduPage;
