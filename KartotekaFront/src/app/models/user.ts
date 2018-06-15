import { UserDTO } from '../models/userDTO';

export class User {
    id: number;
    name: string;
    lastName: string;
    password : string;
    constructor(userDTO : UserDTO){
      this.id = userDTO.id;
      this.name = userDTO.name;
      this.lastName = userDTO.lName;
      this.password= userDTO.password;
    }
    
  }