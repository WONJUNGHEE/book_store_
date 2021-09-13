import { Fragment, useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { useRouter } from 'next/router';

const Purchase = (props: any): JSX.Element => {
	const [booklists, setbooklists] = useState([]);
	const router = useRouter();
	const userId = JSON.parse(sessionStorage.getItem('login_info'));

	useEffect(() => {
		const fetchUsers = async () => {
			try {
				const cartlist = [];
				await axios.get(`http://localhost:50005/${userId[1]}/carts`).then((res) => {
					console.log(res.data);
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

	return (
		<Fragment>
			<Backg>
				<Booktable>
					<thead>
						<tr>
							<th></th>
							<th>제목</th>
							<th>요약</th>
							<th>가격</th>
						</tr>
					</thead>
					<tbody>
						{booklists.map((data) => (
							<tr
								key={data.createdAt}
								onClick={() =>
									router.push({
										pathname: '/DetailBook_page',
										query: { ...data, createdAt: data.createdAt.substring(0, 10) },
									})
								}
							>
								<td>
									<img width="50px" height="70px" src={data.src}></img>
								</td>
								<td>{data.productName}</td>
								<td>{data.detail}</td>
								<td>{data.unitPrice}</td>
								<td>
									<button>삭제</button>
								</td>
							</tr>
						))}
					</tbody>
				</Booktable>

				<InputData>
					<label htmlFor="productId">제품 ID</label>
					<input
						id="productId"
						type="text"
						placeholder="제품 고유 ID"
						onChange={handleproductId}
						onKeyPress={handleKeyPress}
					></input>
					<label htmlFor="title">책 제목</label>
					<input
						id="title"
						type="text"
						placeholder="제목을 입력하세요"
						onChange={handleproductName}
						onKeyPress={handleKeyPress}
					></input>
					<label htmlFor="qty">수량</label>
					<input
						id="qty"
						type="text"
						placeholder="현재 수량을 입력해주세요"
						onChange={handleqty}
						onKeyPress={handleKeyPress}
					></input>
					<label htmlFor="price">가격</label>
					<input
						id="price"
						type="text"
						placeholder="가격을 입력해주세요"
						onChange={handleunitPrice}
						onKeyPress={handleKeyPress}
					></input>
					<label htmlFor="summary">요약</label>
					<input
						id="summary"
						type="text"
						placeholder="요약 내용을 적어주세요"
						onChange={handlesummary}
						onKeyPress={handleKeyPress}
					></input>
					<div>카테고리</div>
					<div>
						<input type="radio" name="my-input" id="edu" value="edu" onChange={handlecategory} />
						<label htmlFor="edu">교육</label>
						<input
							type="radio"
							name="my-input"
							id="cartoon"
							value="cartoon"
							onChange={handlecategory}
						/>
						<label htmlFor="cartoon">만화</label>
						<input
							type="radio"
							name="my-input"
							id="social"
							value="social"
							onChange={handlecategory}
						/>
						<label htmlFor="social">사회</label>
						<ReigsterButton onClick={registerClick}>등록 하기</ReigsterButton>
					</div>
				</InputData>
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

const InputData = styled.div`
	margin: 15px;
	display: flex;
	width: 60%;
	flex-direction: column;
	align-items: center;
	& > input,
	label {
		width: 300px;
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
export default Purchase;
