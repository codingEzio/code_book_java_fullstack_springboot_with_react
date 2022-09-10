import { render, screen } from '@testing-library/react';
import App from './App';

test('renders buttons and tables which has "new car" in it', () => {
  render(<App />);
  const linkElement = screen.getByText(/new car/i);
  expect(linkElement).toBeInTheDocument();
});
