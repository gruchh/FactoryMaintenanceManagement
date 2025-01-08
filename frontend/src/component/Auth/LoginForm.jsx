import { Button, TextField, Typography } from "@mui/material";
import { Field, Form, Formik } from "formik";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";
import { loginUser } from "../State/Authentication/Actions";

export const LoginForm = () => {
  const initialValues = {
    email: "",
    password: "",
  };
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const handleSubmit = (values) => {
    dispatch(loginUser({userData:values, navigate}));
  };


  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email("Invalid email format")
      .required("Email is required"),
    password: Yup.string()
      .min(6, "Password must be at least 6 characters long")
      .required("Password is required"),
  });

  return (
    <div>
      <Typography variant="h5" className="text-center">
        Login
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
              Login
            </Button>
          </Form>
        )}
      </Formik>

      <Typography variant="body2" align="center" style={{ marginTop: "1rem" }}>
        Don't have an account?
        <Button className="mt-5" onClick={() => navigate("/account/register")}>
          REGISTER
        </Button>
      </Typography>
    </div>
  );
};

export default LoginForm;
