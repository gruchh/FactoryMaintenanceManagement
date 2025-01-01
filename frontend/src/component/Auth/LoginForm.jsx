import { Button, TextField, Typography } from "@mui/material";
import { Field, Form, Formik } from "formik";
import * as Yup from "yup";

export const LoginForm = () => {
  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email("Invalid email format")
      .required("Email is required"),
    password: Yup.string()
      .min(6, "Password must be at least 6 characters long")
      .required("Password is required"),
  });

  const handleSubmit = (values) => {
    console.log(values);
  };

  return (
    <div>
      <Typography variant="h5" className="text-center">
        Login
      </Typography>
      <Formik
        initialValues={{
          email: "",
          password: "",
        }}
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
              Submit
            </Button>
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default LoginForm;
