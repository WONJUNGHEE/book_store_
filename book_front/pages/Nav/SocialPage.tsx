import React from 'react';
import Catalog from '../../components/Catalog/Catalog';
import styled from 'styled-components';

const SocialPage = (): JSX.Element => {
	return (
		<CatalogBg>
			<Catalog catal="social" />
		</CatalogBg>
	);
};

const CatalogBg = styled.div``;
export default SocialPage;
