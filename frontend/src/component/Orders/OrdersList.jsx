import {
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
  TextField,
  Typography,
  Button,
} from "@mui/material";
import { useState } from "react";

const testData = [
  { id: 1, nazwa: "Część 1", maszyna: "Maszyna A", cena: 100 },
  { id: 2, nazwa: "Część 2", maszyna: "Maszyna B", cena: 200 },
  { id: 3, nazwa: "Część 3", maszyna: "Maszyna C", cena: 150 },
  { id: 4, nazwa: "Część 4", maszyna: "Maszyna D", cena: 250 },
  { id: 5, nazwa: "Część 5", maszyna: "Maszyna E", cena: 300 },
  { id: 6, nazwa: "Część 6", maszyna: "Maszyna F", cena: 400 },
  { id: 7, nazwa: "Część 7", maszyna: "Maszyna G", cena: 500 },
  { id: 8, nazwa: "Część 8", maszyna: "Maszyna H", cena: 600 },
  { id: 9, nazwa: "Część 9", maszyna: "Maszyna I", cena: 700 },
  { id: 10, nazwa: "Część 10", maszyna: "Maszyna J", cena: 800 },
  { id: 11, nazwa: "Część 11", maszyna: "Maszyna K", cena: 900 },
];

const OrdersList = () => {
  const [searchQuery, setSearchQuery] = useState("");
  const [filteredParts, setFilteredParts] = useState(testData);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  const handleInputChange = (e) => {
    const query = e.target.value;
    setSearchQuery(query);
    const filtered = testData.filter((part) =>
      part.nazwa.toLowerCase().includes(query.toLowerCase())
    );
    setFilteredParts(filtered);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleOrder = (id) => {
    alert(`Część z ID ${id} została zamówiona!`);
  };

  const paginatedParts = filteredParts.slice(
    page * rowsPerPage,
    page * rowsPerPage + rowsPerPage
  );

  return (
    <section>
      <div className="w-full lg:w-[80vw] mx-auto">
        <Typography variant="h4" gutterBottom>
          Wyszukiwarka Części
        </Typography>
        <TextField
          label="Wpisz nazwę części"
          variant="outlined"
          fullWidth
          value={searchQuery}
          onChange={handleInputChange}
          margin="normal"
        />
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Nazwa</TableCell>
                <TableCell>Maszyna</TableCell>
                <TableCell>Cena (PLN)</TableCell>
                <TableCell>Zamów</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {paginatedParts.length > 0 ? (
                paginatedParts.map((part) => (
                  <TableRow key={part.id}>
                    <TableCell>{part.id}</TableCell>
                    <TableCell>{part.nazwa}</TableCell>
                    <TableCell>{part.maszyna}</TableCell>
                    <TableCell>{part.cena}</TableCell>
                    <TableCell>
                      <Button
                        variant="contained"
                        color="primary"
                        onClick={() => handleOrder(part.id)}
                      >
                        Zamów
                      </Button>
                    </TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={5}>
                    <Typography variant="body1">Brak wyników</Typography>
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[10, 20]}
          component="div"
          count={filteredParts.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </div>
    </section>
  );
};

export default OrdersList;
