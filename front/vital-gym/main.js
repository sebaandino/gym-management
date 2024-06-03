// menu descplegable "hamburger"
document.addEventListener('DOMContentLoaded', function () {
  const menuButton = document.getElementById('menu-button');
  const menu = document.getElementById('menu');

  menuButton.addEventListener('click', function () {
    menu.classList.toggle('hidden');
  });
});

// menu acordeon

document.querySelectorAll('.accordion-header').forEach(button => {
  button.addEventListener('click', () => {
    const content = button.nextElementSibling;
    const isOpen = content.style.display === 'block';

    document.querySelectorAll('.accordion-content').forEach(item => {
      item.style.display = 'none';
    });

    if (!isOpen) {
      content.style.display = 'block';
    }
  });
});
