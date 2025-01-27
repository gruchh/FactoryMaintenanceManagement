import Face6Icon from "@mui/icons-material/Face6";
import { Button } from "@mui/material";

const ProfileUser = () => {
  const handleLogout = () => {};
  return (
    <div className="min-h-[80vh] flex flex-col justify-center items-center text-center">
      <div className="flex flex-col items-center justify-center">
        <Face6Icon sx={{ fontSize: "15rem;" }} />
        <div className="py-5 text-2xl font-semibold">gruchh</div>
        <Button onClick={() => handleLogout} sx={{ margin: "2rem 0rem" }}>
          Logout
        </Button>
      </div>
    </div>
  );
};
export default ProfileUser;
