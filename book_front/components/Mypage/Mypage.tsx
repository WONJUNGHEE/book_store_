import React, { useEffect, useState } from 'react';
import { Fragment } from 'react';
import styled from 'styled-components';
import EditUserInfo from 'components/Mypage/EditUserInfo';
import axios from 'axios';

const MyPage = (): JSX.Element => {
	const [name, setName] = useState<string>();
	const [id, setId] = useState<string>();
	const [address, setAddress] = useState<string>();
	const [mail, setMail] = useState<string>();
	const userId = JSON.parse(sessionStorage.getItem('login_info'));
	const [edit, setedit] = useState<boolean>(false);
	const handleedit = () => {
		edit ? setedit(false) : setedit(true);
	};
	axios.get(`http://192.168.35.111:50001/users/${userId[1]}`).then((res) => {
		setName(res.data.name),
			setAddress(res.data.address),
			setId(res.data.myid),
			setMail(res.data.email);
	});

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
						<title>구매내역</title>
						<div>책 이름 : 취업탈출 넘버원</div>
						<div>책 가격 : 100만원</div>
						<div>주문 날짜 : 2021/13/34</div>
					</OrderInfo>
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
export default MyPage;
