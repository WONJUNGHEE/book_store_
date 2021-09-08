import { Fragment } from 'react';
import styled from 'styled-components';

const Cart = (): JSX.Element => {
	return (
		<Fragment>
			<Backg>장바구니</Backg>;
		</Fragment>
	);
};

const Backg = styled.div`
	width: 800px;
	height: 500px;
	margin: 20px auto;
	background-color: whitesmoke;
	border-radius: 20px;
	position: relative;
	& > div {
		position: absolute;
	}
`;

export default Cart;
