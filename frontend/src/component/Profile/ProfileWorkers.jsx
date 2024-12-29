import {
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
  TextField,
} from "@mui/material";
import { useState } from "react";
import { EmployeeExamples } from "./EmployeeExamples";
import { EditNotificationsRounded } from "@mui/icons-material";

const ProfileWorkers = () => {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [filterText, setFilterText] = useState("");

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleEdit = (employeeId) => {
    console.log(`Edytuj pracownika o ID: ${employeeId}`);
  };

  const handleFilterChange = (event) => {
    setFilterText(event.target.value);
  };
  const filteredEmployees = EmployeeExamples.filter(
    (employee) =>
      employee.name.toLowerCase().includes(filterText.toLowerCase()) ||
      employee.surname.toLowerCase().includes(filterText.toLowerCase()) ||
      employee.profession.toLowerCase().includes(filterText.toLowerCase())
  );

  return (
    <Paper>
      <div style={{ padding: "16px" }}>
        <TextField
          label="Filtruj pracowników"
          variant="outlined"
          fullWidth
          value={filterText}
          onChange={handleFilterChange}
        />
      </div>
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Imię</TableCell> <TableCell>Nazwisko</TableCell>
              <TableCell>Stanowisko</TableCell> <TableCell>Akcje</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredEmployees.slice(
              page * rowsPerPage,
              page * rowsPerPage + rowsPerPage
            ).map((employee) => (
              <TableRow key={employee.id}>
                <TableCell>{employee.name}</TableCell>
                <TableCell>{employee.surname}</TableCell>
                <TableCell>{employee.profession}</TableCell>
                <TableCell>
                  <Button
                    onClick={() => handleEdit(employee.id)}
                    startIcon={<EditNotificationsRounded />}
                  >
                    Edytuj
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 20, 50]}
        component="div"
        count={EmployeeExamples.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default ProfileWorkers;
