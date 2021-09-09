import React from 'react';
import All_book from '../components/Catalog/All_book';
import styled from 'styled-components';

const CatalogPage = () => {
	return (
		<Catalog>
			<All_book />
		</Catalog>
	);
};

const Catalog = styled.div``;
export default CatalogPage;
