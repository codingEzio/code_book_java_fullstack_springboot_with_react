import React, { useState, useEffect } from 'react';
import { SERVER_URL } from '../constants';

const Carlist = () => {
  const [cars, setCars] = useState([]);

  useEffect(() => {
    fetch(SERVER_URL + 'api/cars')
      .then(response => response.json())
      .then(data => setCars(data._embedded.cars))
      .catch(error => console.error(error));
  });

  return (
    <div>
      <table>
        <tbody>
          {cars.map((car, index) => (
            <tr key={index}>
              <td>{car.brand}</td>
              <td>{car.model}</td>
              <td>{car.released_at}</td>
              <td>{car.color}</td>
              <td>{car.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Carlist;
