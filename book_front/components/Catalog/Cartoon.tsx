import { Fragment, useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const Cartoon = (): JSX.Element => {
	const [booklists, setbooklists] = useState([]);
	useEffect(() => {
		const fetchUsers = async () => {
			try {
				const booklist = [];
				const list1 = await axios.get('http://192.168.35.111:50101/catalogs');
				for (const book of list1.data) {
					console.log(book);
					if (book.category === 'study')
						booklist.push(
							<Bookinfo>
								<img width="50px" height="70px"></img>
								<Detail>
									<div>
										책 이름 : {book.productName} 가격 :{book.unitPrice}
									</div>
									<div>
										이 책을 공부하면 네카라는 물론 삼성 현대 가고 싶은 회사를 갈 수
										있습니다.가나다라마바사아자차카타타카한이정니앚,으마ㅓㄴ어지이아ㅣ나인아
									</div>
								</Detail>
							</Bookinfo>,
						);
				}
				setbooklists(booklist);
			} catch (e) {
				console.log(e);
			}
		};
		fetchUsers();
	}, []);
	return (
		<Fragment>
			<Backg>{booklists}</Backg>
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
const Bookinfo = styled.div`
	display: flex;
`;
const Detail = styled.div`
	margin: 10px;
`;
export default Cartoon;
