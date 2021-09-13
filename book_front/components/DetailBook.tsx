import { Fragment, useState } from 'react';
import axios from 'axios';
import styled from 'styled-components';

const DetailBook = (props): JSX.Element => {
	const [bookcnt, setbookcnt] = useState(1);
	const userId = JSON.parse(sessionStorage.getItem('login_info'));
	const handlebookcnt = (e) => {
		setbookcnt(e.target.value);
	};
	const cartgo = async () => {
		try {
			await axios
				.post(`http://localhost:50005/${userId[1]}/carts`, {
					productId: props.data.productId,
					productName: props.data.productName,
					qty: bookcnt,
					unitPrice: props.data.unitPrice,
				})
				.then((res) => {
					console.log(res);
				});
		} catch (e) {
			console.log(e);
			console.log(props.productId);
		}
	};

	const purchchase = async () => {
		try {
			await axios
				.post(`http://localhost:40000/${userId[1]}/orders`, {
					productId: props.data.productId,
					productName: props.data.productName,
					qty: bookcnt,
					unitPrice: props.data.unitPrice,
				})
				.then((res) => {
					console.log(res);
				});
		} catch (e) {
			console.log(e);
			console.log(props.productId);
		}
	};
	const handlecart = () => {
		cartgo();
		alert('장바구니에 담았습니다.');
	};
	const handlepurchase = () => {
		purchchase();
		alert('구매완료');
	};
	return (
		<Fragment>
			<Backg>
				<Wrap>
					<Book_image>
						<img
							width="300px"
							height="400px"
							className="profile_preview"
							src={props.data.src}
						></img>
					</Book_image>
					<InputData>
						<h2>{props.data.productName}</h2>
						<h3>등록일</h3>
						<span>{props.data.createdAt}</span>
						<h3>가격</h3>
						<div>{props.data.unitPrice}</div>
						<h3>줄거리</h3>
						<div>{props.data.detail}</div>
						<label htmlFor="cnt">수량</label>
						<input type="number" placeholder="1" id="cnt" onChange={handlebookcnt} />
						<div>
							<button onClick={handlepurchase}>구매하기</button>
							<button onClick={handlecart}>장바구니</button>
						</div>
					</InputData>
				</Wrap>
			</Backg>
		</Fragment>
	);
};

const Backg = styled.div`
	width: 800px;
	height: 570px;
	margin: 20px auto;
	background-color: whitesmoke;
	border-radius: 20px;
	position: relative;
	& > div {
		position: absolute;
	}
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
const Book_image = styled.div`
	display: flex;
	flex-direction: column;
	width: 40%;
	height: 100%;
	padding-left: 20px;
	padding-right: 20px;
	justify-content: center;
	border-right: 1px solid red;
	& > img {
		padding-bottom: 15px;
	}
`;
// const Book_info = styled.div`
// 	width: 100%;
// 	height: 100%;
// 	padding-left: 20px;
// `;
const InputData = styled.div`
	margin: 15px;
	display: flex;
	width: 60%;
	flex-direction: column;
	align-items: center;
	& > input,
	label {
		width: 100px;
		padding: 5px;
		margin: 5px;
		text-align: center;
	}
	& > div {
		width: 300px;
		margin: 10px;
		flex-direction: row;
	}
`;

const ReigsterButton = styled.button`
	margin-top: 15px;
	width: 150px;
	padding: 6px 12px;
	color: #fff;
	background-color: cadetblue;
	border-radius: 5px;
	font-size: 13px;
	border: none;
`;
export default DetailBook;
