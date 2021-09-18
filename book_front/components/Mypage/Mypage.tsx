import React, { useEffect, useState } from 'react';
import { Fragment } from 'react';
import styled from 'styled-components';
import EditUserInfo from './EditUserInfo';
import axios from 'axios';
import { paginate } from '../../utils/paginate';
import Pagination from '../Catalog/Pagination';

const MyPage = (): JSX.Element => {
	const [name, setName] = useState<string>();
	const [id, setId] = useState<string>();
	const [address, setAddress] = useState<string>();
	const [mail, setMail] = useState<string>();
	const userId = JSON.parse(sessionStorage.getItem('login_info'));
	const [edit, setedit] = useState<boolean>(false);
	const [ordered, setordered] = useState({ orderdata: [], pageSize: 4, currentPage: 1 });

	useEffect(() => {
		const myinfo = async () => {
			try {
				const orderlist = [];
				const config = {
					headers: {
						Authorization: 'Bearer ' + userId[0],
					},
				};
				const orderedd = await axios.get(
					`http://localhost:8000/user-service/users/${userId[1]}`,
					config,
				);

				setName(orderedd.data.userName),
					setAddress(orderedd.data.address),
					setId(orderedd.data.myId),
					setMail(orderedd.data.email);
				let cnt = 0;
				for (const list of orderedd.data.orders) {
					orderlist.push(
						<OrderList key={cnt++}>
							<div>책 이름 : {list.productId}</div>
							<div>수량 : {list.qty}</div>
							<div>구매 가격 : {list.totalPrice}원</div>
							<div>주문 날짜 : {list.orderedAt}</div>
						</OrderList>,
					);
				}
				setordered({ orderdata: orderlist, pageSize: 4, currentPage: 1 });
				edit ? setedit(false) : setedit(true);
			} catch (e) {
				console.log(e);
			}
		};
		myinfo();
	}, []);

	const handlePageChange = (page) => {
		setordered({ ...ordered, currentPage: page });
	};
	const { orderdata, pageSize, currentPage } = ordered;
	const pagedordered = paginate(orderdata, currentPage, pageSize);
	const { length: count } = ordered.orderdata;
	return (
		<Fragment>
			<Backg>
				<Wrap>
					<UserInfo>
						<title>회원 정보</title>
						<div>아이디 : {id}</div>
						<div>비밀번호 : ********</div>
						<div>이름 : {name}</div>
						<div>이메일 : {mail}</div>
						<div>주소 : {address}</div>
						<EditUserInfo />
					</UserInfo>
					<OrderInfo>
						<h2>주문 내역</h2>
						<div>{pagedordered}</div>
					</OrderInfo>
					<Pagination
						pageSize={pageSize}
						itemsCount={count}
						currentPage={currentPage}
						onPageChange={handlePageChange}
					/>
				</Wrap>
			</Backg>
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
`;
const Wrap = styled.div`
	display: flex;
	flex-direction: row;
	height: 460px;
	margin: 20px;
`;
const UserInfo = styled.div`
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 100%;
	padding-left: 20px;
	justify-content: center;
	border-right: 1px solid red;
	& > div {
		padding-bottom: 15px;
	}
`;
const OrderInfo = styled.div`
	width: 100%;
	height: 100%;
	padding-left: 20px;
`;
const EditBnt = styled.button`
	margin-top: 15px;
	margin-left: 30px;
	width: 200px;
	padding: 6px 12px;
	color: #fff;
	background-color: cadetblue;
	border-radius: 5px;
	font-size: 13px;
	border: none;
`;
const OrderList = styled.div`
	padding: 10px;
`;

export default MyPage;
