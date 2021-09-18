import { Fragment, useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { useRouter } from 'next/router';

const Cart = (props: any): JSX.Element => {
	const { catal } = props;
	const [booklists, setbooklists] = useState([]);

	const router = useRouter();
	const userId = JSON.parse(sessionStorage.getItem('login_info'));

	useEffect(() => {
		const fetchUsers = async () => {
			try {
				const cartlist = [];
				await axios.get(`http://localhost:8000/cart-service/${userId[1]}/carts`).then((res) => {
					for (const cart of res.data) {
						cartlist.push(cart);
					}
					setbooklists(cartlist);
				});
			} catch (e) {
				console.log(e);
			}
		};
		fetchUsers();
	}, []);
	const cartdel = async (e) => {
		try {
			await axios
				.delete(`http://localhost:8000/cart-service/${userId[1]}/carts/${e.target.value}`)
				.then((res) => {
					alert('삭제되었습니다.');
				});
		} catch (error) {
			console.log(error);
		}
	};
	const cartorders = async (e) => {
		try {
			await axios
				.post(`http://localhost:8000/order-service/${userId[1]}/carts/orders`)
				.then((res) => {
					alert('주문이 완료 되었습니다.');
				});
		} catch (error) {
			console.log(error);
		}
	};
	return (
		<Fragment>
			<Backg>
				<h2>장바구니</h2>
				<Booktable>
					<thead>
						<tr>
							<th></th>
							<th>제목</th>
							<th>가격</th>
							<th>개수</th>
						</tr>
					</thead>
					<tbody>
						{booklists.map((data) => (
							<tr key={data.productId}>
								<td>
									<img width="50px" height="70px" src={data.src}></img>
								</td>
								<td>{data.productName}</td>
								<td>{data.unitPrice}</td>
								<td>{data.qty}</td>
								<td>
									<DetailButton value={data.productName} onClick={cartdel}>
										삭제
									</DetailButton>
								</td>
							</tr>
						))}
					</tbody>
				</Booktable>
				<DetailButton onClick={cartorders}>주문하기</DetailButton>
			</Backg>
		</Fragment>
	);
};

const Backg = styled.div`
	width: 800px;
	height: 90%;
	margin: 20px auto;
	background-color: whitesmoke;
	border-radius: 20px;
	position: relative;
	padding: 20px;
`;

const Booktable = styled.table`
	width: 100%;
	text-align: center;
	padding: 10px;
	border: 1px solid goldenrod;
	border-radius: 10px;
`;
const DetailButton = styled.button`
	margin: 15px;
	width: 100px;
	padding: 6px 12px;
	color: #fff;
	background-color: cadetblue;
	border-radius: 5px;
	font-size: 13px;
	border: none;
`;
export default Cart;
