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
				await axios.get(`http://localhost:50005/${userId[1]}/carts`).then((res) => {
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
				.delete(`http://localhost:50005/${userId[1]}/carts/${e.target.value}`)
				.then((res) => {
					alert('삭제되었습니다.');
				});
		} catch (error) {
			console.log(error);
		}
	};
	const cartorders = async (e) => {
		try {
			await axios.post(`http://localhost:40000/${userId[1]}/carts/orders`).then((res) => {
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
									<button value={data.productName} onClick={cartdel}>
										삭제
									</button>
								</td>
							</tr>
						))}
					</tbody>
				</Booktable>
				<button onClick={cartorders}>주문하기</button>
			</Backg>
		</Fragment>
	);
};

// const list1 = await axios.get('http://localhost:50101/catalogs');

// for (const book of list1.data) {
// 	let detaillength;
// 	if (catal == 'All') {
// 		book.detail.length > 20
// 			? (detaillength = book.detail.substr(0, 20) + '...')
// 			: (detaillength = book.detail);
// 		booklist.push({
// 			...book,
// 			detail: detaillength,
// 		});
// 	} else if (book.category === catal) {
// 		book.detail.length > 20
// 			? (detaillength = book.detail.substr(0, 20) + '...')
// 			: (detaillength = book.detail);
// 		booklist.push({
// 			...book,
// 			detail: detaillength,
// 		});
// 	}
// }

const Backg = styled.div`
	width: 800px;
	height: 90%;
	margin: 20px auto;
	background-color: whitesmoke;
	border-radius: 20px;
	position: relative;
	padding: 20px;
`;
const Bookinfo = styled.div`
	display: flex;
`;
const Detail = styled.div`
	margin: 10px;
`;
const Booktable = styled.table`
	width: 100%;
	text-align: center;
	padding: 10px;
	border: 1px solid goldenrod;
	border-radius: 10px;
`;
const Wrap = styled.div`
	display: flex;
	flex-direction: row;
	height: 510px;
	width: 100%;
	margin: 20px;
	text-align: center;
	font-size: 15px;
`;
export default Cart;
