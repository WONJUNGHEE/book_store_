import { Fragment, useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { paginate } from '../../utils/paginate';
import Pagination from './Pagination';

const All_book = (): JSX.Element => {
	const [booklists, setbooklists] = useState({ bookdata: [], pageSize: 3, currentPage: 1 });
	useEffect(() => {
		const fetchUsers = async () => {
			try {
				const booklist = [];
				const list1 = await axios.get('http://192.168.35.111:50101/catalogs');

				for (const book of list1.data) {
					let detaillength;
					if (book.category === 'social') {
						book.detail.length > 20
							? (detaillength = book.detail.substr(0, 20) + '...')
							: (detaillength = book.detail);
						booklist.push({
							...book,
							detail: detaillength,
						});
					}
				}
				setbooklists({ bookdata: booklist, pageSize: 3, currentPage: 1 });
			} catch (e) {
				console.log(e);
			}
		};
		fetchUsers();
	}, []);

	const handlePageChange = (page) => {
		setbooklists({ ...booklists, currentPage: page });
	};
	const { bookdata, pageSize, currentPage } = booklists;
	const pagedBooks = paginate(bookdata, currentPage, pageSize);

	const { length: count } = booklists.bookdata;
	if (count === 0)
		return (
			<Fragment>
				<Backg>
					<Booktable>
						<p>등록된 책이 없습니다. 정보가 없습니다.</p>;
					</Booktable>
				</Backg>
			</Fragment>
		);
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
						{pagedBooks.map((data) => (
							<tr key={data.createdAt}>
								<td>
									<img width="50px" height="70px" src={data.src}></img>
								</td>
								<td>{data.productName}</td>
								<td>{data.detail}</td>
								<td>{data.unitPrice}</td>
							</tr>
						))}
					</tbody>
				</Booktable>
				<Pagination
					pageSize={pageSize}
					itemsCount={count}
					currentPage={currentPage}
					onPageChange={handlePageChange}
				/>
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
export default All_book;
