import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';

test('renders buttons and tables which has "new car" in it', () => {
  render(<App />);
  const linkElement = screen.getByText(/new car/i);

  expect(linkElement).toBeInTheDocument();
});

test('open add car model form', async () => {
  render(<App />);
  fireEvent.click(screen.getByText('New Car'));

  expect(screen.getByRole('dialog')).toHaveTextContent('New car');
});
