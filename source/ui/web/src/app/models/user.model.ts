export interface UserModel {
  id: number;
  firstname: string;
  lastname: string;
  username: string;
  email: string;
  profilePicture: Uint8Array; // or use 'string' if the picture is stored as a base64 string
  status: string;
}