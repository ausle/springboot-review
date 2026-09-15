
// 定义一个接口，用于限定person对象的格式
export interface Person{
    id:string,
    name:string,
    age:number
}


export type Persons = Array<Person>

