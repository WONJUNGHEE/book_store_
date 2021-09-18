import React from 'react';
import Catalog from '../../components/Catalog/Catalog';
import styled from 'styled-components';

const SocialPage = (): JSX.Element => {
	return (
		<CatalogBg>
			<Catalog catal="social" header="사회" />
		</CatalogBg>
	);
};

const CatalogBg = styled.div``;
export default SocialPage;
