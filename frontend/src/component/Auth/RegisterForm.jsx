import { Button, TextField, Typography } from "@mui/material";
import { Field, Form, Formik } from "formik";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";
import { registerUser } from "../State/Authentication/Actions";

export const RegisterForm = () => {
  const initialValues = {
    fullName: "",
    email: "",
    password: "",
    role: "ROLE_USER",
  };

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = (values) => {
    dispatch(registerUser({userData:values, navigate}));
  };

  const validationSchema = Yup.object({
    fullName: Yup.string()
      .required("Full name is required")
      .min(5, "Full name must be at least 5 characters long"),
    email: Yup.string()
      .email("Invalid email format")
      .required("Email is required"),
    password: Yup.string()
      .required("Password is required")
      .min(6, "Password must be at least 6 characters long"),
  });
  return (
    <div>
      <Typography variant="h5" className="text-center">
        Register
      </Typography>
      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        {({ errors, touched }) => (
          <Form>
            <Field
              as={TextField}
              name="fullName"
              label="FullName"
              fullWidth
              variant="outlined"
              margin="normal"
              error={touched.fullName && !!errors.fullName}
              helperText={touched.fullName && errors.fullName}
            />
            <Field
              as={TextField}
              name="email"
              label="Email"
              fullWidth
              variant="outlined"
              margin="normal"
              error={touched.email && !!errors.email}
              helperText={touched.email && errors.email}
            />
            <Field
              as={TextField}
              name="password"
              label="Password"
              type="password"
              fullWidth
              variant="outlined"
              margin="normal"
              error={touched.password && !!errors.password}
              helperText={touched.password && errors.password}
            />
            <Button fullWidth type="submit" variant="contained">
              Register
            </Button>
          </Form>
        )}
      </Formik>
      <Typography variant="body2" align="center" style={{ marginTop: "1rem" }}>
        Have you already account?
        <Button onClick={() => navigate("/account/login")}>LOGIN</Button>
      </Typography>
    </div>
  );
};
