import React from 'react';
import DetailBook from '../components/DetailBook';
import { useRouter } from 'next/router';

const Cart_page = (): JSX.Element => {
	const router = useRouter();

	return (
		<div>
			<DetailBook data={router.query} />
		</div>
	);
};

export default Cart_page;
